package services;

import data.HealthCardID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;

import java.util.List;

public class HealthNationalServiceClass implements HealthNationalService {

    private HealthCardID hcID;
    private List<ProductSpecification> specification;
    private MedicalPrescription prescription;

    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException{
        try {
            if (!prescription.getHcID(hcID)){
                hcID = null;
                throw new HealthCardException("error");
            }
            if (prescription.getHcID(hcID) == null){
                hcID = null;
                throw new NotValidePrescriptionException("error");
            }
        }catch (HealthCardException e) {
            throw new HealthCardException("error");
        } catch (NotValidePrescriptionException e) {
            throw new NotValidePrescriptionException("error");
        } catch (Exception e) {
            hcID = null;
            throw new ConnectException("error");
        }
    }

    public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException{

    }

    public ProductSpecification getProductSpecific(int opt)  throws AnyMedicineSearchException, ConnectException{

    }

    public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription{

    }
}
