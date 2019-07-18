package handson.impl;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.commands.Command;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerDraft;
import io.sphere.sdk.customers.CustomerDraftDsl;
import io.sphere.sdk.customers.CustomerName;
import io.sphere.sdk.customers.CustomerSignInResult;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.customers.commands.CustomerCreateCommand;
import io.sphere.sdk.customers.commands.CustomerCreateEmailTokenCommand;
import io.sphere.sdk.customers.commands.CustomerVerifyEmailCommand;
import io.sphere.sdk.models.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;

import com.neovisionaries.i18n.CountryCode;

/**
 * This class provides operations to work with {@link Customer}s.
 */
public class CustomerService extends AbstractService {

    public CustomerService(final SphereClient client) {
        super(client);
    }

    /**
     * Creates a new customer {@link Customer} with the given parameters.
     *
     * @param email    the customers email
     * @param password the customers password
     * @return the customer creation completion stage
     */
    public CompletionStage<CustomerSignInResult> createCustomer(final String email, final String password) {
        
    	final CustomerName name = CustomerName.ofFirstAndLastName("Kapil", "Dev");
    	final Address shippingAddress= Address.of(CountryCode.DE).withAdditionalAddressInfo("HCL Tech Chicago United states");
    	List<Address> addresses=Arrays.asList(Address.of(CountryCode.DE),Address.of(CountryCode.US)) ;
    	//shippingAddresses.add(shippingAddress.;
		/*CustomerDraft draft = CustomerDraftDsl.of(name, email, password)
				.withShippingAddresses(Collections.singletonList(2))
				.withAddresses(addresses);*/
    	CustomerDraft draft = CustomerDraftDsl.of(name, email, password)
				.withAddresses(addresses);
        final CustomerCreateCommand sphereRequest = CustomerCreateCommand.of(draft);
        final CompletionStage<CustomerSignInResult> result = client.execute(sphereRequest);
        return result;
    }

    /**
     * Creates an email verification token for the given customer.
     * This is then used to create a password reset link.
     *
     * @param customer            the customer
     * @param timeToLiveInMinutes the time to live (in minutes) for the token
     * @return the customer token creation completion stage
     */
    public CompletionStage<CustomerToken> createEmailVerificationToken(final Customer customer, final Integer timeToLiveInMinutes) {
       System.out.println(""+customer);
       
       Command<CustomerToken> createTokenCommand =
               CustomerCreateEmailTokenCommand.ofCustomerId(customer.getId(), timeToLiveInMinutes);
       final CompletionStage<CustomerToken> customerToken= client.execute(createTokenCommand);
       return customerToken;
    }

    /**
     * Verifies the customer token.
     *
     * @param customerToken the customer token
     * @return the email verification completion stage
     */
    public CompletionStage<Customer> verifyEmail(final CustomerToken customerToken) {
    	 final Command<Customer> verifyEmailCommand = CustomerVerifyEmailCommand.ofTokenValue(customerToken.getValue());
    	 final CompletionStage<Customer> customer= client.execute(verifyEmailCommand);
        return customer;
    }
}
