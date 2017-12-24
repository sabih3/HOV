package ae.netaq.homesorder_vendor.network.core;

import java.io.IOException;
import java.lang.annotation.Annotation;

import ae.netaq.homesorder_vendor.network.model.APIError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by sabih on 24-Dec-17.
 */

public class ErrorUtils {

    public static APIError parseError(Response<?> response){

        Converter<ResponseBody, APIError> converter =
                RestClient.getRetrofit().responseBodyConverter(APIError.class, new Annotation[0]);


        APIError error =new APIError();

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {

        }

        return error;
    }
}
