package au.gov.digitalhealth.medserve.extension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateType;
import org.hl7.fhir.dstu3.model.Medication;
import org.hl7.fhir.instance.model.api.IBaseResource;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.util.ElementUtil;

@ResourceDef(name = "Medication", id = "extendedMedication", profile = ExtendedMedication.PROFILE_URL_BASE
        + "Profile/ExtendedMedication")
public class ExtendedMedication extends Medication implements IBaseResource, ParentExtendedElement, ResourceWithHistoricalAssociations {

    public static final String PROFILE_URL_BASE = "http://medserve.online/fhir/";
    private static final long serialVersionUID = 1L;

    @Child(name = "parentMedicationResources", min = 0, max = Child.MAX_UNLIMITED, summary = false)
    @Extension(url = PROFILE_URL_BASE
            + "StructureDefinition/parentMedicationResources", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "A collections of medication resources that represent an abstraction of this medication resource")
    private List<MedicationParentExtension> parentMedicationResources;

    @Child(name = "generalizedMedicine", min = 1, max = Child.MAX_UNLIMITED, summary = true)
    @Extension(url = PROFILE_URL_BASE
            + "StructureDefinition/generalizedMedicine", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "A 'leaf-most' medication resources that represent an abstraction of this medication resource")
    private List<GeneralizedMedication> generalizedMedicine;

    /**
     * A code that indicates the level of abstraction of the medication
     */
    @Child(name = "medicationResourceType", min = 1, max = 1, summary = true)
    @Extension(url = PROFILE_URL_BASE
            + "StructureDefinition/medicationResourceType", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "A code that indicates what level of abstraction the medication represents")
    private Coding medicationResourceType;

    /**
     * A CodeableConcept for the medication brand
     */
    @Child(name = "brand", min = 0, max = 1, summary = false)
    @Extension(url = PROFILE_URL_BASE + "StructureDefinition/brand", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "A CodeableConcept to identify the brand of the medication if it is branded")
    private CodeableConcept brand;

    /**
     * The code system that was the source of this {@link ExtendedMedication} resource.
     */
    @Child(name = "sourceCodeSystem", min = 1, max = 1, summary = false)
    @Extension(url = PROFILE_URL_BASE
            + "StructureDefinition/sourceCodeSystem", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "The code system that was the source of this {@link ExtendedMedication} resource.")
    private SourceCodeSystemExtension sourceCodeSystem;

    /**
     * Subsidy
     */
    @Child(name = "subsidies", min = 0, max = 1, summary = false)
    @Extension(url = PROFILE_URL_BASE + "StructureDefinition/subsidy", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "An extension which captures subsidy coding and associated information for this Medication ")
    private List<SubsidyExtension> subsidies;

    /**
     * The date the underlying definition of the concept or its descriptions were last changed.
     */
    @Child(name = "lastModified", min = 1, max = 1, summary = false)
    @Extension(url = PROFILE_URL_BASE + "StructureDefinition/lastModified", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "The date the underlying definition of the concept or its descriptions were last changed.")
    private DateType lastModified;

    /**
     * Replacement resources for this resource
     */
    @Child(name = "isReplacedByResources", min = 1, max = 1, summary = false)
    @Extension(url = ExtendedMedication.PROFILE_URL_BASE
            + "StructureDefinition/isReplacedByResources", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "Replacement resources for this resource")
    private List<IsReplacedByExtension> isReplacedByResources;

    /**
     * Resources that this resource has replaced
     */
    @Child(name = "replacesResources", min = 1, max = 1, summary = false)
    @Extension(url = ExtendedMedication.PROFILE_URL_BASE
            + "StructureDefinition/replacesResources", definedLocally = false, isModifier = false)
    @Description(shortDefinition = "Resources that this resource has replaced")
    private List<ReplacesResourceExtension> replacesResources;

    @Override
    public boolean isEmpty() {
        return super.isEmpty() && ElementUtil.isEmpty(medicationResourceType, parentMedicationResources, brand,
            sourceCodeSystem, subsidies, lastModified, isReplacedByResources, replacesResources, generalizedMedicine);
    }

    @Override
    public Collection<MedicationParentExtension> getParentMedicationResources() {
        if (parentMedicationResources == null) {
            parentMedicationResources = new ArrayList<>();
        }
        return parentMedicationResources;
    }

    @Override
    public void setParentMedicationResources(List<MedicationParentExtension> parentMedicationResources) {
        this.parentMedicationResources = parentMedicationResources;
    }

    @Override
    public void addParentMedicationResources(MedicationParentExtension parentMedicationResource) {
        getParentMedicationResources().add(parentMedicationResource);
    }

    public Coding getMedicationResourceType() {
        if (medicationResourceType == null) {
            medicationResourceType = new Coding();
        }
        return medicationResourceType;
    }

    public void setMedicationResourceType(Coding medicationResourceType) {
        this.medicationResourceType = medicationResourceType;
    }

    public CodeableConcept getBrand() {
        return brand;
    }

    public void setBrand(CodeableConcept brand) {
        this.brand = brand;
    }

    public List<SubsidyExtension> getSubsidies() {
        if (subsidies == null) {
            subsidies = new ArrayList<>();
        }
        return subsidies;
    }

    public void addSubsidy(SubsidyExtension subsidy) {
        this.subsidies.add(subsidy);
    }

    public SourceCodeSystemExtension getSourceCodeSystem() {
        return sourceCodeSystem;
    }

    public void setSourceCodeSystem(SourceCodeSystemExtension sourceCodeSystem) {
        this.sourceCodeSystem = sourceCodeSystem;
    }

    public DateType getLastModified() {
        if (lastModified == null) {
            lastModified = new DateType();
        }
        return lastModified;
    }

    public void setLastModified(DateType lastModified) {
        this.lastModified = lastModified;
    }

    /* (non-Javadoc)
     * @see au.gov.digitalhealth.medserve.extension.ResourceWithHistoricalAssociations#getReplacementResources()
     */
    @Override
    public List<IsReplacedByExtension> getReplacementResources() {
        if (isReplacedByResources == null) {
            isReplacedByResources = new ArrayList<>();
        }
        return isReplacedByResources;
    }

    /* (non-Javadoc)
     * @see au.gov.digitalhealth.medserve.extension.ResourceWithHistoricalAssociations#setReplacementResources(java.util.List)
     */
    @Override
    public void setReplacementResources(List<IsReplacedByExtension> replacementResources) {
        this.isReplacedByResources = replacementResources;
    }

    /* (non-Javadoc)
     * @see au.gov.digitalhealth.medserve.extension.ResourceWithHistoricalAssociations#getReplacedResources()
     */
    @Override
    public List<ReplacesResourceExtension> getReplacedResources() {
        if (replacesResources == null) {
            replacesResources = new ArrayList<>();
        }
        return replacesResources;
    }

    /* (non-Javadoc)
     * @see au.gov.digitalhealth.medserve.extension.ResourceWithHistoricalAssociations#setReplacedResources(java.util.List)
     */
    @Override
    public void setReplacedResources(List<ReplacesResourceExtension> replacedResources) {
        this.replacesResources = replacedResources;
    }

    public List<GeneralizedMedication> getGeneralizedMedicine() {
        if (generalizedMedicine == null) {
            generalizedMedicine = new ArrayList<>();
        }
        return generalizedMedicine;
    }

    public void setGeneralizedMedicine(List<GeneralizedMedication> generalizedMedicine) {
        this.generalizedMedicine = generalizedMedicine;
    }

}
