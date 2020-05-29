package fr.sma.bw.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ObjectMapperUtils {

    private static ModelMapper modelMapper = new ModelMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperUtils() {}
    public static  <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }
    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }
    public static <D> D mapJson(final String entity, Class<D> outClass)  {
        try {
            return objectMapper.readValue(entity, outClass);
        }
        catch(Exception e) {
            return null;
        }
    }

    public static <T> List<T> mapJsonAsList(final String entity, Class<T> outClass)  {
        try {
            Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + outClass.getName() + ";");
            T[] objects = objectMapper.readValue(entity, arrayClass);
            return Arrays.asList(objects);
        }
        catch(ClassNotFoundException cnfe) {
            return null;
        }
        catch(JsonProcessingException jpe) {
            return null;
        }
    }
}
