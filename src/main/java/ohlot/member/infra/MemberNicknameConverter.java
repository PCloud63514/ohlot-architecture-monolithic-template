package ohlot.member.infra;

import ohlot.member.domain.MemberNickname;

import javax.persistence.AttributeConverter;

class MemberNicknameConverter implements AttributeConverter<MemberNickname, String> {
    @Override
    public String convertToDatabaseColumn(final MemberNickname attribute) {
        return null == attribute ? null : attribute.value();
    }

    @Override
    public MemberNickname convertToEntityAttribute(final String dbData) {
        return new MemberNickname(dbData);
    }
}
