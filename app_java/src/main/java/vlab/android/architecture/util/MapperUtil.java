package vlab.android.architecture.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class MapperUtil {

    /**
     * map list data
     * @param source source items
     * @param mapper
     * @param <InputType>
     * @param <OutputType>
     * @return List<OutputType> list of OutputType
     */
    public static <InputType, OutputType> List<OutputType> mapList(List<InputType> source, IMapper<InputType, OutputType> mapper) {
        ArrayList<OutputType> result = new ArrayList<>(source.size());
        for (InputType item : source) {
            result.add(mapper.map(item));
        }
        return result;
    }

    public interface IMapper<InputType, OutputType> {
        OutputType map(InputType inputObject);
    }
}
