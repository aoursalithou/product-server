package org.product.productserver.service.token;


import org.product.productserver.domain.token.ConfirmationToken;
import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token) ;

    int setConfirmedAt(String token);
}