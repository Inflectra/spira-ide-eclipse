/**
 * 
 */
package com.inflectra.spirateam.mylyn.core.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.commons.core.StatusHandler;
import org.eclipse.mylyn.commons.net.AbstractWebLocation;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.tasks.core.IRepositoryListener;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.core.TaskRepositoryLocationFactory;

import com.inflectra.spirateam.mylyn.core.internal.services.SpiraAuthenticationException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraConnectionException;
import com.inflectra.spirateam.mylyn.core.internal.services.SpiraImportExport;

/**
 * @author Inflectra Corporation
 *
 */
public class SpiraTeamClientManager implements IRepositoryListener
{
	private final Map<String, SpiraImportExport> clientByUrl = new HashMap<String, SpiraImportExport>();
	private final Map<String, SpiraTeamClientData> clientDataByUrl = new HashMap<String, SpiraTeamClientData>();
	private final File cacheFile;
	private volatile TaskRepositoryLocationFactory taskRepositoryLocationFactory;

	public SpiraTeamClientManager(File cacheFile, TaskRepositoryLocationFactory taskRepositoryLocationFactory)
	{
		this.cacheFile = cacheFile;
		this.taskRepositoryLocationFactory = taskRepositoryLocationFactory;

		readCache();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.IRepositoryListener#repositoryAdded(org.eclipse.mylyn.tasks.core.TaskRepository)
	 */
	@Override
	public void repositoryAdded(TaskRepository repository)
	{
		// make sure there is no stale client still in the cache
		removeClient(repository);
		clientDataByUrl.remove(repository.getRepositoryUrl());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.IRepositoryListener#repositoryRemoved(org.eclipse.mylyn.tasks.core.TaskRepository)
	 */
	@Override
	public void repositoryRemoved(TaskRepository repository)
	{
		removeClient(repository);
		clientDataByUrl.remove(repository.getRepositoryUrl());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.IRepositoryListener#repositorySettingsChanged(org.eclipse.mylyn.tasks.core.TaskRepository)
	 */
	@Override
	public void repositorySettingsChanged(TaskRepository repository)
	{
		removeClient(repository);
		// if url is changed a stale data object will be left in
		// clientDataByUrl
	}

	/* (non-Javadoc)
	 * @see org.eclipse.mylyn.tasks.core.IRepositoryListener#repositoryUrlChanged(org.eclipse.mylyn.tasks.core.TaskRepository, java.lang.String)
	 */
	@Override
	public void repositoryUrlChanged(TaskRepository repository, String oldUrl)
	{
		// ignore
	}
	
	public synchronized void clearClients()
	{
		clientByUrl.clear();
	}

	private synchronized void removeClient(TaskRepository repository)
	{
		clientByUrl.remove(repository.getRepositoryUrl());
	}
	
	public synchronized void readCache()
	{
		if (cacheFile == null || !cacheFile.exists())
		{
			return;
		}

		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(cacheFile));
			int size = in.readInt();
			for (int i = 0; i < size; i++)
			{
				String url = (String) in.readObject();
				SpiraTeamClientData data = (SpiraTeamClientData) in.readObject();
				if (url != null && data != null)
				{
					clientDataByUrl.put(url, data);
				}
			}
		}
		catch (Throwable e)
		{
			StatusHandler.log(new Status(IStatus.WARNING, SpiraTeamCorePlugin.PLUGIN_ID,
					Messages.SpiraTeamClientManager_CacheNotReadable, e));
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					// ignore
				}
			}
		}

	}

	public synchronized void writeCache()
	{
		if (cacheFile == null)
		{
			return;
		}

		ObjectOutput out = null;
		try
		{
			out = new ObjectOutputStream(new FileOutputStream(cacheFile));
			out.writeInt(clientDataByUrl.size());
			for (String url : clientDataByUrl.keySet())
			{
				out.writeObject(url);
				out.writeObject(clientDataByUrl.get(url));
			}
		}
		catch (IOException e)
		{
			StatusHandler.log(new Status(IStatus.WARNING, SpiraTeamCorePlugin.PLUGIN_ID,
					Messages.SpiraTeamClientManager_CacheNotWritable, e));
		}
		finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				}
				catch (IOException e)
				{
					// ignore
				}
			}
		}
	}
	
	public synchronized SpiraImportExport getSpiraTeamClient(TaskRepository taskRepository)
		throws SpiraAuthenticationException, SpiraConnectionException, MalformedURLException
	{
		String url = taskRepository.getRepositoryUrl();
		SpiraImportExport client = clientByUrl.get(url);
		if (client == null)
		{
			//TODO: Implement client-server versioning (for now we just create using new)
			//client = TracClientFactory.createClient(location, Version.fromVersion(taskRepository.getVersion()));
			AuthenticationCredentials credentials = taskRepository.getCredentials(AuthenticationType.REPOSITORY);
			if (credentials == null)
			{
				throw new SpiraAuthenticationException(Messages.SpiraTeamClientManager_MissingCredentials);
			}
			String userName = credentials.getUserName();
			String password = credentials.getPassword();
			client = new SpiraImportExport(url, userName, password);
			clientByUrl.put(taskRepository.getRepositoryUrl(), client);

			SpiraTeamClientData data = clientDataByUrl.get(taskRepository.getRepositoryUrl());
			if (data == null)
			{
				data = new SpiraTeamClientData();
				clientDataByUrl.put(taskRepository.getRepositoryUrl(), data);
			}
			client.setData(data);
		}
		else
		{
			//Update the credentials in case they have been changed
			AuthenticationCredentials credentials = taskRepository.getCredentials(AuthenticationType.REPOSITORY);
			if (credentials != null)
			{
				client.setUserName(credentials.getUserName());
				client.setPassword(credentials.getPassword());
			}
		}
		return client;
	}
}
