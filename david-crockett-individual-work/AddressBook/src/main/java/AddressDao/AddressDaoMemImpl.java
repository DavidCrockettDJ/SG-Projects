/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddressDao;

import Model.Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class AddressDaoMemImpl implements DaoInterface{
    Map<Integer, Address> addressMap = new HashMap<>();
    int id = 1;

    @Override
    public Address addAddress(Address newAddress) {
        newAddress.setId(id);
        addressMap.put(newAddress.getId(), newAddress);
        id++;
        return newAddress;
    }

    @Override
    public void deleteAddress(int id) {
        addressMap.remove(id);
    }

    @Override
    public Address editAddress(Address address) {
        addressMap.put(address.getId(), address);
        return address;
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addressList = new ArrayList<>(addressMap.values());
        return addressList;
    }
    
    
}
