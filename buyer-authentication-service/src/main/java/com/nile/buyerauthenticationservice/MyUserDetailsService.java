package com.nile.buyerauthenticationservice;

import com.nile.buyerauthenticationservice.models.Buyer;
import com.nile.buyerauthenticationservice.models.BuyerRepository;
import com.nile.buyerauthenticationservice.models.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Buyer> buyer = buyerRepository.findByUserName(userName);

        buyer.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return buyer.map(MyUserDetails::new).get();
    }
}
