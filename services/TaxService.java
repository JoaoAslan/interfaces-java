package aula1.services;

// Removes dependency on a class with an important method,
// in cases of changes, there is no risk of errors occurring
public interface TaxService {

    double tax(double amount);
}
