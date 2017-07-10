/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddressDao;

import Model.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DaoInterface {
    
    public Address addAddress(Address newAddress);
    
    public void deleteAddress(int id);
    
    public Address editAddress(Address address);
    
    public List<Address> getAllAddresses();
}
