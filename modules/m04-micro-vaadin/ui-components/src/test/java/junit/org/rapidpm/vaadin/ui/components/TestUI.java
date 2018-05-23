package junit.org.rapidpm.vaadin.ui.components;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.rapidpm.vaadin.addons.framework.Registration;
import org.rapidpm.vaadin.shared.Customer;
import org.rapidpm.vaadin.shared.CustomerStatus;
import org.rapidpm.vaadin.ui.components.CustomerForm;

import java.time.LocalDate;

import static java.lang.System.out;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;
import static org.rapidpm.vaadin.addons.framework.ComponentIDGenerator.*;

/**
 *
 */
@PreserveOnRefresh
public class TestUI extends UI {

  public static final String TEST_SWITCH_BUTTON = buttonID().apply(TestUI.class, "testSwitchButton");
  public static final String REGISTER_BUTTON    = buttonID().apply(TestUI.class, "testRegisterButton");
  public static final String FIRST_NAME         = textfieldID().apply(TestUI.class, "firstName");
  public static final String LAST_NAME          = textfieldID().apply(TestUI.class, "lastName");
  public static final String EMAIL              = textfieldID().apply(TestUI.class, "email");
  public static final String BIRTHDAY           = dateFieldID().apply(TestUI.class, "birthday");
  public static final String CUSTOMER_FORM      = genericID().apply(CustomerForm.class, TestUI.class, "customerForm");
  public static final String ID                 = textfieldID().apply(TestUI.class, "customerID");


  private final TextField                    firstName    = new TextField("First name");
  private final TextField                    lastName     = new TextField("Last name");
  private final TextField                    email        = new TextField("Email");
  private final NativeSelect<CustomerStatus> status       = new NativeSelect<>("Status");
  private final DateField                    birthday     = new DateField("Birthday");
  private final TextField                    id           = new TextField("Customer ID");
  private final Binder<Customer>             beanBinder   = new Binder<>(Customer.class);
  private final CustomerForm                 customerForm = new CustomerForm();

  private Customer fromLastEvent;

  private Registration deleteRegistration;
  private Registration saveRegistration;

  @Override
  protected void init(VaadinRequest request) {
    beanBinder
        .forField(id)
        .withConverter(new StringToLongConverter("Long Values only"))
        .bind(Customer::getId, Customer::setId);

    firstName.setId(FIRST_NAME);
    lastName.setId(LAST_NAME);
    email.setId(EMAIL);
    birthday.setId(BIRTHDAY);
    id.setId(ID);

    status.setItems(CustomerStatus.values());

    initCustomerValue();
    customerForm.setId(CUSTOMER_FORM);
    customerForm.setCustomer(fromLastEvent);

    final CustomerForm.UpdateEvent updateEvenDelete = customer -> {
      fromLastEvent = customer;
      fromLastEvent.setId(-1L);
      beanBinder.setBean(fromLastEvent);
    };
    deleteRegistration = customerForm.registerDeleteListener(updateEvenDelete);

    final CustomerForm.UpdateEvent updateEvenSave = customer -> {
      fromLastEvent = customer;
      fromLastEvent.setId((fromLastEvent == null) ? 1L : fromLastEvent.getId() + 1);
      beanBinder.setBean(fromLastEvent);
    };
    saveRegistration = customerForm.registerSaveListener(updateEvenSave);


    beanBinder.bindInstanceFields(this);

    // button to make Form visible again
    final Button aSwitch = new Button("switch");
    aSwitch.setId(TEST_SWITCH_BUTTON);
    aSwitch.addClickListener((Button.ClickListener) event -> customerForm.setCustomer(fromLastEvent));

    final Button register = new Button("register");
    register.setId(REGISTER_BUTTON);
    register.addClickListener(
        e ->
            match(
                matchCase(() -> failure(" an exceptional behavior...")),
                matchCase(() -> deleteRegistration != null &&
                                saveRegistration != null, () -> {
                  final boolean removeDelete = deleteRegistration.remove();
                  final boolean removeSave   = saveRegistration.remove();

                  deleteRegistration = null;
                  saveRegistration = null;

                  return (removeDelete && removeSave)
                         ? success("Both registrations are removed")
                         : failure("Mistake removeDelete=" + removeDelete
                                   + " removeSave " + removeSave);
                }),
                matchCase(() -> deleteRegistration == null
                                && saveRegistration == null, () -> {

                  deleteRegistration = customerForm.registerDeleteListener(updateEvenDelete);
                  saveRegistration = customerForm.registerSaveListener(updateEvenSave);

                  return (deleteRegistration != null && saveRegistration != null)
                         ? success("Both registrations are done")
                         : failure("Mistake deleteRegistration=" + deleteRegistration
                                   + " saveRegistration " + saveRegistration);
                })
            ).ifPresentOrElse(
                success -> out.println("register Button - success = " + success),
                failed -> { throw new RuntimeException(failed); }
            )
    );

    final VerticalLayout testAttibutes = new VerticalLayout(id,
                                                            firstName,
                                                            lastName,
                                                            email,
                                                            birthday,
                                                            status
    );

    final VerticalLayout layout = new VerticalLayout(customerForm, aSwitch, register, testAttibutes);

    setContent(layout);
  }

  private void initCustomerValue() {
    fromLastEvent = new Customer();
    fromLastEvent.setBirthDay(LocalDate.of(2010, 10, 10));
    fromLastEvent.setEmail("xx.xxx@xx.xx");
    fromLastEvent.setFirstName("Max");
    fromLastEvent.setLastName("R");
    fromLastEvent.setStatus(CustomerStatus.ImportedLead);
    fromLastEvent.setId(1L);
  }


  @Override
  public void detach() {
    if (!deleteRegistration.remove()) throw new RuntimeException("DeleteRegistration failed to remove");
    if (!saveRegistration.remove()) throw new RuntimeException("SaveRegistration failed to remove");
    super.detach();
  }
}
