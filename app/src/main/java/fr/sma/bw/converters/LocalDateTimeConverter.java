package fr.sma.bw.converters;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeConverter implements AttributeConverter < LocalDateTime, Timestamp > {
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute != null ? Timestamp.valueOf(attribute) : null;
    }
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData != null ? dbData.toLocalDateTime() : null;
    }
}
