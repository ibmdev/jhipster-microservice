package fr.sma.bw.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ObjectMapperUtils {

    private static ModelMapper modelMapper = new ModelMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    private ObjectMapperUtils() {}
    
    public static  <D, T> D map(final T entity, final Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }
    public static <S, D> D map(final S source, final D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
    public static <D, T> List<D> mapAll(final Collection<T> entityList, final Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }
    public static <T> T mapJson(final String entity, final Class<T> outClass)  {
        try {
            return objectMapper.readValue(entity, outClass);
        }
        catch(JsonProcessingException jpe) {
        	log.error("Erreur de mapping Json", jpe);
            return null;
        }
    }
}
