package eu.laramartin.booklisting;


import eu.laramartin.booklisting.model.BookSearchResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Miquel Beltran on 9/17/16
 * More on http://beltran.work
 */
public interface GoogleBooksService {
    @GET("books/v1/volumes")
    Observable<BookSearchResult> search(@Query("q") String search);
}
