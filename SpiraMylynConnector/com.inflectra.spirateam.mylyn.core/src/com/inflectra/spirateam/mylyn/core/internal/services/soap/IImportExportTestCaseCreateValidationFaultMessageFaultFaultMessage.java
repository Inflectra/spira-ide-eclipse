
package com.inflectra.spirateam.mylyn.core.internal.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ValidationFaultMessage", targetNamespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v4_0")
public class IImportExportTestCaseCreateValidationFaultMessageFaultFaultMessage
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ValidationFaultMessage faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public IImportExportTestCaseCreateValidationFaultMessageFaultFaultMessage(String message, ValidationFaultMessage faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public IImportExportTestCaseCreateValidationFaultMessageFaultFaultMessage(String message, ValidationFaultMessage faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.inflectra.spirateam.mylyn.core.internal.services.soap.ValidationFaultMessage
     */
    public ValidationFaultMessage getFaultInfo() {
        return faultInfo;
    }

}
