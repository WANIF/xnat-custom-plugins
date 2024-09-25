package org.nrg.xnat.plugins.template.dcm;

import org.apache.commons.lang3.StringUtils;
import org.dcm4che2.data.DicomObject;
import org.nrg.dcm.MatchedPatternExtractor;
import org.nrg.dcm.TextExtractor;

public final class ConfigurableMappedAttributeExtractor extends TextExtractor {
    private static final String UNKNOWN = "unknown";

    private final MatchedPatternExtractor              _numberExtractor;
    private final ConfigurableMappedAttributeExtractor _otherTextExtractor;

    public ConfigurableMappedAttributeExtractor(final int tag, final MatchedPatternExtractor numberExtractor) {
        super(tag);
        _numberExtractor = numberExtractor;
        _otherTextExtractor = null;
    }

    public ConfigurableMappedAttributeExtractor(final int tag, final ConfigurableMappedAttributeExtractor otherTextExtractor) {
        super(tag);
        _otherTextExtractor = otherTextExtractor;
        _numberExtractor = null;
    }

    public ConfigurableMappedAttributeExtractor(final int tag) {
        super(tag);
        _otherTextExtractor = null;
        _numberExtractor = null;
    }

    @Override
    public String extract(final DicomObject dicom) {
        // If the tag is empty, return null. 
        final String content = dicom.getString(getTag());

        if (StringUtils.isNotBlank(content)) {
            if (_numberExtractor != null) {
                return format(_numberExtractor.extract(dicom), content);
            }
            if (_otherTextExtractor != null) {
                return format(_otherTextExtractor.extract(dicom), content);
            }
            return content;
        }

        if (_numberExtractor != null) {
            final String number = _numberExtractor.extract(dicom);
            if (StringUtils.isNotBlank(number)) {
                return number;
            }
        }

        return UNKNOWN;
    }

    private String format(final String prefix, final String content) {
        return StringUtils.isNotBlank(prefix) ? prefix + '_' + content : content;
    }

    private int getTag() {
        return getTags().first();
    }
}