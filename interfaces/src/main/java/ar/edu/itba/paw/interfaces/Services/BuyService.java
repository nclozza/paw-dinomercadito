package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Buy;

import java.util.List;
import java.util.Optional;

public interface BuyService {

    public Buy createBuy(final Integer postId, final Integer buyerUserId, final Integer productQuantity, final Double price);

    public boolean deleteBuy(final Integer buyId);

    public Optional<Buy> findBuyByBuyId(final Integer buyId);

    public List<Buy> findBuysByBuyerUserId(final Integer buyerUserId);

    public Integer buyTransaction(final Integer buyerUserId, final Integer postId, final Integer productQuantity);
}

