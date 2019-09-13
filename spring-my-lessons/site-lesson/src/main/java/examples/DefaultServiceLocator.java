package examples;

public class DefaultServiceLocator {

    private static ClientService clientService = ClientService.createInstance();

    public ClientService createClientServiceInstance() {
        return clientService;
    }

    public ClientService createAccountServiceInstance() {
        return clientService;
    }
}