package org.nrg.dcm.xnat;

import org.dcm4che2.data.Tag;
import org.nrg.dcm.AttrDefs;
import org.nrg.dcm.MutableAttrDefs;
import org.nrg.xdat.bean.*;
import org.springframework.stereotype.Component;

import static org.nrg.dcm.DicomAttributes.MR_DIFF_ANISOTROPY_TYPE;
import static org.nrg.dcm.DicomAttributes.MR_DIFF_DIRECTION;
import static org.nrg.dcm.DicomAttributes.MR_DIFF_ORIENTATION;
import static org.nrg.dcm.DicomAttributes.MR_FLIP_ANGLE;
import static org.nrg.dcm.DicomAttributes.MR_INVERSION_TIME;
import static org.nrg.dcm.DicomAttributes.MR_PIXEL_BANDWIDTH;
import static org.nrg.dcm.DicomAttributes.MR_RECEIVE_COIL_NAME;
import static org.nrg.dcm.DicomAttributes.MR_REPETITION_TIME;

@Component
public class MRScanAttributesBValFix extends DICOMImageScanAttributes {

    public MRScanAttributesBValFix() {
        super(XnatMrscandataBean.class);
    }

    @Override
    public AttrDefs get() {
        return s;
    }

    static final private MutableAttrDefs s = new MutableAttrDefs(ImageScanAttributes.get());

    static {
        s.add(new VoxelResAttribute("parameters/voxelRes"));
        s.add(new OrientationAttribute("parameters/orientation"));
        s.add("coil", MR_RECEIVE_COIL_NAME);
        s.add(new MagneticFieldStrengthAttribute());
        s.add(new XnatAttrDef.Real("parameters/tr", MR_REPETITION_TIME));
        s.add(new MREchoTimeAttribute());
        s.add(new XnatAttrDef.Real("parameters/ti", MR_INVERSION_TIME));
        s.add(new XnatAttrDef.Real("parameters/flip", MR_FLIP_ANGLE));
        s.add("parameters/sequence", Tag.SequenceName);
        s.add("parameters/imageType", Tag.ImageType);
        s.add("parameters/scanSequence", Tag.ScanningSequence);
        s.add("parameters/seqVariant", Tag.SequenceVariant);
        s.add("parameters/scanOptions", Tag.ScanOptions);
        s.add("parameters/acqType", Tag.MRAcquisitionType);
        s.add(new XnatAttrDef.Real("parameters/pixelBandwidth", MR_PIXEL_BANDWIDTH));
        s.add(new ImageFOVAttribute("parameters/fov"));

        //s.add("parameters/diffusion/directionality", MR_DIFF_DIRECTION);
        //s.add("parameters/diffusion/orientations", MR_DIFF_ORIENTATION);
        //s.add("parameters/diffusion/anisotropyType", MR_DIFF_ANISOTROPY_TYPE);
    }
}
