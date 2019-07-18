package handson.impl;

import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.queries.CategoryQuery;
import io.sphere.sdk.categories.queries.CategoryQueryModel;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.queries.ProductProjectionQuery;
import io.sphere.sdk.products.search.ProductProjectionSearch;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.queries.Query;
import io.sphere.sdk.queries.QueryPredicate;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.CompletionStage;

/**
 * This class provides query operations for {@link ProductProjection}s.
 */
public class ProductQueryService extends AbstractService {

    public ProductQueryService(SphereClient client) {
        super(client);
    }

    /**
     * @param locale
     * @param name
     * @return
     */
    private CompletionStage<PagedQueryResult<Category>> findCategory(final Locale locale, final String name) {
    	Query<Category> query = CategoryQuery.of().byName(locale, name);
    	CompletionStage<PagedQueryResult<Category>> future = client.execute(query);
    	
        return future;
    }

    /**
     * Queries product projections that belong to given category
     * @param category
     * @return Paged result of Product projections
     */
    private CompletionStage<PagedQueryResult<ProductProjection>> withCategory(final Category category) {
    	//ProductProjectionSearch productProjectionSearch = ProductProjectionSearch.ofCurrent();
    	//QueryPredicate<ProductProjection> queryPredicate = CategoryQueryModel.of().name().
    	ProductProjectionQuery productProjectionQuery = ProductProjectionQuery.ofCurrent().withPredicates(m->m.categories().isIn(Arrays.asList(category)));
    	CompletionStage<PagedQueryResult<ProductProjection>> result = client.execute(productProjectionQuery);
    	return result;
    }

    /**
     * Finds products with categories that have the given localized name.
     *
     * @param locale the locale
     * @param name   the localized name
     * @return the product query completion stage
     */
    public CompletionStage<PagedQueryResult<ProductProjection>> findProductsWithCategory(final Locale locale, final String name) {
        // TODO 4.3 Find a product with category
        return null;
    }
}
