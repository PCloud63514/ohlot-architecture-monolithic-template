package ohlot.member.infra;

import ohlot.member.domain.MemberSecureId;

import javax.persistence.AttributeConverter;

class MemberSecureIdConverter implements AttributeConverter<MemberSecureId, String> {
    @Override
    public String convertToDatabaseColumn(final MemberSecureId attribute) {
        return null == attribute ? null : attribute.value();
    }

    @Override
    public MemberSecureId convertToEntityAttribute(final String dbData) {
        return new MemberSecureId(dbData);
    }
}
