
package serverCom.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for soapableMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="soapableMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="abtNr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="gruppen" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inhalt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="messageID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="oeffentlich" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "soapableMessage", propOrder = {
    "abtNr",
    "gruppen",
    "inhalt",
    "messageID",
    "oeffentlich",
    "time",
    "userID"
})
public class SoapableMessage {

    protected int abtNr;
    @XmlElement(nillable = true)
    protected List<Integer> gruppen;
    protected String inhalt;
    protected int messageID;
    protected boolean oeffentlich;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar time;
    protected int userID;

    /**
     * Gets the value of the abtNr property.
     * 
     */
    public int getAbtNr() {
        return abtNr;
    }

    /**
     * Sets the value of the abtNr property.
     * 
     */
    public void setAbtNr(int value) {
        this.abtNr = value;
    }

    /**
     * Gets the value of the gruppen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gruppen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGruppen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getGruppen() {
        if (gruppen == null) {
            gruppen = new ArrayList<Integer>();
        }
        return this.gruppen;
    }

    /**
     * Gets the value of the inhalt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInhalt() {
        return inhalt;
    }

    /**
     * Sets the value of the inhalt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInhalt(String value) {
        this.inhalt = value;
    }

    /**
     * Gets the value of the messageID property.
     * 
     */
    public int getMessageID() {
        return messageID;
    }

    /**
     * Sets the value of the messageID property.
     * 
     */
    public void setMessageID(int value) {
        this.messageID = value;
    }

    /**
     * Gets the value of the oeffentlich property.
     * 
     */
    public boolean isOeffentlich() {
        return oeffentlich;
    }

    /**
     * Sets the value of the oeffentlich property.
     * 
     */
    public void setOeffentlich(boolean value) {
        this.oeffentlich = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     */
    public void setUserID(int value) {
        this.userID = value;
    }

}
