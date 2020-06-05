package fr.sma.bw.converters;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeConverter implements AttributeConverter < LocalDateTime, Timestamp > {
	
	@Override
    public Timestamp convertToDatabaseColumn(final LocalDateTime attribute) {
    	return (attribute == null) ? null : Timestamp.valueOf(attribute);
        
    }
    @Override
    public LocalDateTime convertToEntityAttribute(final Timestamp dbData) {
        return (dbData == null) ? null : dbData.toLocalDateTime();
    }
}
