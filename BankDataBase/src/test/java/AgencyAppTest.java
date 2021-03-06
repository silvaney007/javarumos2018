import Domain.*;
import Service.AccountApp;
import Service.AgencyApp;
import com.github.javafaker.Faker;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AgencyAppTest {

        @Test
        public void newBank_addSingleAgency_returnSingleAgency(){

            Faker faker = new Faker();
            AgencyApp agencyService = new AgencyApp();
            Bank bank = new Bank(faker.company().name());
            Agency agency = new Agency(faker.address().fullAddress());

            agencyService.setBankAgencies(bank, agency);

            assertThat(bank.getAgencies()).containsExactly(agency);
        }


        @Test
        public void newBank_addMultipleAgency_returnAllAgencies(){

            Faker faker = new Faker();
            AgencyApp agencyService = new AgencyApp();
            Bank bank = new Bank(faker.company().name());
            Agency firstAgency = new Agency(faker.address().fullAddress());
            Agency secondAgency = new Agency(faker.address().fullAddress());

            agencyService.setBankAgencies(bank, firstAgency);
            agencyService.setBankAgencies(bank, secondAgency);

            assertThat(bank.getAgencies())
                    .containsExactly(new Agency[]{firstAgency, secondAgency});
        }

    @Test
    public void clientGoesToBankAgency_toORegister_returnsWithValidRegist() {

        Faker faker = new Faker();

        //behind desk work done by AgencyService
        AgencyApp agencyService = new AgencyApp();
        AccountApp accountService = new AccountApp();

        //Cristiano Ronaldo
        Client cr7 = new Client("Cristiano Ronaldo",faker.idNumber().ssnValid(),faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(),faker.job().title());
        Agency novoBanco = new Agency(faker.address().fullAddress());

        // register client to agency
        Client cr7client = agencyService.registerClient(novoBanco, cr7);

        //ask Cristiano Ronaldo
        System.out.println("Client:" + cr7.toString());
        System.out.println();
        assertThat(cr7).isEqualToComparingOnlyGivenFields(cr7client);

        //ask bank agency
        System.out.println("Agency Clients:" + novoBanco.toString());
        System.out.println();

    }

}
