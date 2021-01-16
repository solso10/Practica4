package services;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;

import java.math.BigDecimal;
import java.util.*;

public class HealthNationalServiceClass implements HealthNationalService {


    private Map<HealthCardID, MedicalPrescription> prescriptions = new HashMap<>();
    private Map<String, List<ProductSpecification>> products = new HashMap<>();

    private List<ProductSpecification> newProducts;
    private int newPresCode = 0;
    private HealthCardID newHealthCard;
    private MedicalPrescription newMedicalPrescription;

    public HealthNationalServiceClass() throws Exception, IncorrectTakingGuidelinesException {
        setUpPrescriptions();
        setUpProducts();
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException{
        try {
            if (!prescriptions.containsKey(hcID)){
                newHealthCard = null;
                throw new HealthCardException("error 1");
            }
            if (prescriptions.get(hcID) == null){
                newHealthCard = null;
                throw new NotValidePrescriptionException("error 2");
            }
        }catch (HealthCardException e) {
            newHealthCard = null;
            throw new HealthCardException("error 3");
        } catch (NotValidePrescriptionException e) {
            newHealthCard = null;
            throw new NotValidePrescriptionException("error 4");
        } catch (Exception e) {
            newHealthCard = null;
            throw new ConnectException("error 5");
        }
        newHealthCard = hcID;
        return prescriptions.get(hcID);
    }

    @Override
    public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException{

        try {
            if(products.get(keyWord) == null){
                newProducts = null;
                throw new AnyKeyWordMedicineException("Error");
            }

            if(!products.containsKey(keyWord)){
                newProducts = null;
                throw new AnyKeyWordMedicineException("Error");
            }
        } catch (AnyKeyWordMedicineException e) {
            newProducts = null;
            throw new AnyKeyWordMedicineException("error");
        } catch (Exception e) {
            newProducts = null;
            throw new ConnectException("error");
        }

        newProducts = products.get(keyWord);
        return products.get(keyWord);

    }

    @Override
    public ProductSpecification getProductSpecific(int opt)  throws AnyMedicineSearchException, ConnectException{
        try {
            if(newProducts.isEmpty()) throw new AnyMedicineSearchException("Error");

            if(newProducts == null) throw new AnyMedicineSearchException("Error");

            if(opt > newProducts.size()) throw new AnyMedicineSearchException("Error");

            if(newProducts.get(opt) == null) throw new AnyMedicineSearchException("Error");

        } catch (AnyMedicineSearchException e) {
            throw new AnyMedicineSearchException("error");
        } catch (Exception e) {
            throw new ConnectException("error");
        }

        return newProducts.get(opt);
    }

    @Override
    public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription{
        try {
            if(ePresc.getPrescriptionLines().isEmpty()) throw new NotValidePrescription("Error");
            if(ePresc.getPrescriptionLines() == null) throw new NotValidePrescription("Error");
            if(ePresc.geteSign() == null) throw new eSignatureException("Error");
            if(ePresc.getHcID() == null) throw new NotCompletedMedicalPrescription("Error");
            if(ePresc.getPrescDate() == null) throw new NotCompletedMedicalPrescription("Error");
            if(ePresc.getEndDate() == null) throw new NotCompletedMedicalPrescription("Error");

            newPresCode += 1;
            newMedicalPrescription = new MedicalPrescription(newPresCode, ePresc.getPrescDate(), ePresc.getEndDate(), ePresc.getHcID(), ePresc.geteSign());
            prescriptions.put(newHealthCard, newMedicalPrescription);
        } catch (NotValidePrescription e) {
            throw new NotValidePrescription("error");
        } catch (eSignatureException e) {
            throw new eSignatureException("error");
        } catch (NotCompletedMedicalPrescription e) {
            throw new NotCompletedMedicalPrescription("error");
        } catch (Exception e) {
            throw new ConnectException("error");
        }

        return newMedicalPrescription;
    }


    private void setUpPrescriptions() throws ProductIDException, IncorrectTakingGuidelinesException{

        HealthCardID hcdi1 = new HealthCardID("ABCD1234567890");
        HealthCardID hcdi2 = new HealthCardID("EFGH2345678901");
//        HealthCardID hcdi3 = new HealthCardID("IJKL3456789012");

        DigitalSignature ds = new DigitalSignature("DR".getBytes());

        MedicalPrescription mp1 = new MedicalPrescription(1234, new Date(2020,12,20), new Date(2020,12,30), hcdi1, ds);
        MedicalPrescription mp2 = new MedicalPrescription(5678, new Date(2020,10,10), new Date(2020,11,20), hcdi2, ds);
//        MedicalPrescription mp3 = new MedicalPrescription(9012, new Date(2020,9,6), new Date(2020,10,9), hcdi3, ds);

        ProductID prodID1 = new ProductID("1010115");
        ProductID prodID2 = new ProductID("1010116");
        ProductID prodID3 = new ProductID("1010117");
        ProductID prodID4 = new ProductID("1010118");

        String[] array1 = new String[]{"AFTERBREAKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
        String[] array2 = new String[]{"BEFOREBREAKFAST", "3","medicamento para  el dolor de pecho","3","6", "HOUR"};
        String[] array3 = new String[]{"DURINGBREAKFAST", "4","medicamento para  el dolor de corazon","4","5", "WEEK"};
        String[] array4 = new String[]{"AFTERDINNER", "5","medicamento para  el dolor de pie","7","8", "WEEK"};

        mp1.addLine(prodID1,array1);
        mp1.addLine(prodID2,array2);
        mp2.addLine(prodID3,array3);
        mp2.addLine(prodID4,array4);

        prescriptions.put(hcdi1, mp1);
        prescriptions.put(hcdi2, mp2);


    }

    private void setUpProducts() throws ProductIDException{

        ProductID prodID1 = new ProductID("1010115");
        ProductID prodID2 = new ProductID("1010116");

        String desc1 = "Ingerir via oral";
        String desc2 = "Aplicar de forma cutanea";

        ProductSpecification ps1 = new ProductSpecification(prodID1, desc1, new BigDecimal(10.0));
        ProductSpecification ps2 = new ProductSpecification(prodID2, desc2, new BigDecimal(4.5));

        products.put("Medicamento 1", new ArrayList<ProductSpecification>(List.of(ps1)));
        products.put("Medicamento 2", new ArrayList<ProductSpecification>(List.of(ps2)));
    }

}
