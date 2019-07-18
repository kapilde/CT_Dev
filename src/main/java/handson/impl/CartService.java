package handson.impl;

import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.carts.CartDraft;
import io.sphere.sdk.carts.LineItemDraft;
import io.sphere.sdk.carts.commands.CartCreateCommand;
import io.sphere.sdk.carts.commands.CartUpdateCommand;
import io.sphere.sdk.carts.commands.updateactions.AddLineItem;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.models.DefaultCurrencyUnits;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.ProductVariant;

import java.util.List;
import java.util.concurrent.CompletionStage;

import com.neovisionaries.i18n.CountryCode;

/**
 * This class provides operations to work with {@link Cart}s.
 */
public class CartService extends AbstractService {

    public CartService(SphereClient client) {
        super(client);
    }

    /**
     * Creates a cart for the given customer.
     *
     * @param customer the customer
     * @return the customer creation completion stage
     */
    public CompletionStage<Cart> createCart(final Customer customer) {
    	final CartDraft cartDraft = CartDraft.of(DefaultCurrencyUnits.USD).withCountry(CountryCode.US);
    	final CompletionStage<Cart> cart = client.execute(CartCreateCommand.of(cartDraft));
        return cart;
    }

    /**
     * Adds the given product to the given cart.
     *
     * @param product the product
     * @param cart    the cart
     * @return the cart update completion stage
     */
    public CompletionStage<Cart> addProductToCart(final ProductProjection product, final Cart cart) {
    	List<ProductVariant> varientList = product.getAllVariants();
    	 final AddLineItem lineItemDraft1 = AddLineItem.of(product.getId(), varientList.get(0).getId(), 1);
    	 
    	 final CartUpdateCommand cartUpdateCommand = CartUpdateCommand.of(cart, lineItemDraft1);
    	 final CompletionStage<Cart> updatedCart = client.execute(cartUpdateCommand);
			return updatedCart; 
	
    }

    /**
     * Adds the given discount code to the given cart.
     *
     * @param code the discount code
     * @param cart the cart
     * @return the cart update completion stage
     */
    public CompletionStage<Cart> addDiscountToCart(final String code, final Cart cart) {
        // TODO 5.1 Add discount code to cart
        return null;
    }
}
