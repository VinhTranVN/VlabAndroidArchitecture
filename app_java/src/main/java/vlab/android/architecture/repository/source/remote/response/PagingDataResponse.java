package vlab.android.architecture.repository.source.remote.response;

import java.util.List;

/**
 * Created by Vinh Tran on 10/30/18.
 */
public class PagingDataResponse<DataResponse> {
    private String mNextPage;
    private List<DataResponse> mItems;

    public PagingDataResponse(List<DataResponse> items, String nextPage) {
        mItems = items;
        mNextPage = nextPage;
    }

    public String getNextPage() {
        return mNextPage;
    }

    public List<DataResponse> getItems() {
        return mItems;
    }
}
