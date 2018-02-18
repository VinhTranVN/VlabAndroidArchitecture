package vlab.android.common.model;

/**
 * This class hold the result of request
 *
 * Created by Vinh Tran on 2/18/18.
 */
public class Response<Data> {

    private Data data;
    private Throwable error;

    public Response(Data data, Throwable error) {
        this.data = data;
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
