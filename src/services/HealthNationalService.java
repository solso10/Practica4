package services;

/**
 * External service for managing and storing ePrescriptions from population
 */

public interface HealthNationalService {

    MedicalPrescription getePrescription(HealthCardID hcID)
            throws HealthCardException, NotValidePrescriptionException, ConnectException;
    List<ProductSpecification> getProductsByKW(String keyWord)
            throws AnyKeyWordMedicineException, ConnectException;
    ProductSpecification getProductSpecific(int opt)
            throws AnyMedicineSearchException, ConnectException;
    MedicalPrescription6 sendePrescription(MedicalPrescription7 ePresc)
            throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription;

}
