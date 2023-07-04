package ohlot.member.infra;

import ohlot.member.domain.MemberStateMessage;

import javax.persistence.AttributeConverter;

class MemberStateMessageConverter implements AttributeConverter<MemberStateMessage, String> {
    @Override
    public String convertToDatabaseColumn(final MemberStateMessage attribute) {
        return null == attribute ? null : attribute.value();
    }

    @Override
    public MemberStateMessage convertToEntityAttribute(final String dbData) {
        return new MemberStateMessage(dbData);
    }
}
