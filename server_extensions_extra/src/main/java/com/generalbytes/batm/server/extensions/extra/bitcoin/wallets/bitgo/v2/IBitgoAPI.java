/*************************************************************************************
 * Copyright (C) 2014-2025 GENERAL BYTES s.r.o. All rights reserved.
 *
 * This software may be distributed and modified under the terms of the GNU
 * General Public License version 2 (GPL2) as published by the Free Software
 * Foundation and appearing in the file GPL2.TXT included in the packaging of
 * this file. Please note that GPL2 Section 2[b] requires that all works based
 * on this software must also be made publicly available under the terms of
 * the GPL2 ("Copyleft").
 *
 * Contact information
 * -------------------
 *
 * GENERAL BYTES s.r.o.
 * Web      :  http://www.generalbytes.com
 *
 ************************************************************************************/
package com.generalbytes.batm.server.extensions.extra.bitcoin.wallets.bitgo.v2;

import com.generalbytes.batm.server.extensions.extra.bitcoin.wallets.bitgo.v2.dto.BitGoAddressResponse;
import com.generalbytes.batm.server.extensions.extra.bitcoin.wallets.bitgo.v2.dto.BitGoCoinRequest;
import com.generalbytes.batm.server.extensions.extra.bitcoin.wallets.bitgo.v2.dto.BitGoCreateAddressRequest;
import com.generalbytes.batm.server.extensions.extra.bitcoin.wallets.bitgo.v2.dto.BitGoSendManyRequest;
import com.generalbytes.batm.server.extensions.extra.bitcoin.wallets.bitgo.v2.dto.BitGoTransfersResponse;
import com.generalbytes.batm.server.extensions.extra.bitcoin.wallets.bitgo.v2.dto.ErrorResponseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;

@Path("/api/v2")
@Produces(MediaType.APPLICATION_JSON)
public interface IBitgoAPI {

    @POST
    @Path("/{coin}/wallet/{id}/sendmany")
    @Consumes(MediaType.APPLICATION_JSON)
    Map<String, Object> sendMany(@PathParam("coin") String coin, @PathParam("id") String id, BitGoSendManyRequest request) throws IOException, ErrorResponseException;

    @POST
    @Path("/{coin}/wallet/{id}/sendcoins")
    @Consumes(MediaType.APPLICATION_JSON)
    Map<String, Object> sendCoins(@PathParam("coin") String coin, @PathParam("id") String id, BitGoCoinRequest request) throws IOException, ErrorResponseException;

    @GET
    @Path("/{coin}/wallet/balances")
    Map<String, Object> getTotalBalances(@PathParam("coin") String coin) throws IOException, ErrorResponseException;

    @GET
    @Path("/{coin}/wallet")
    Map<String, Object> getWallets(@PathParam("coin") String coin) throws IOException, ErrorResponseException;

    @GET
    @Path("/{coin}/wallet/{id}")
    Map<String, Object> getWalletById(@PathParam("coin") String coin, @PathParam("id") String id) throws IOException, ErrorResponseException;

    @GET
    @Path("/{coin}/wallet/{walletId}/transfer")
    BitGoTransfersResponse getTransfers(@PathParam("coin") String coin,
                                        @PathParam("walletId") String walletId,
                                        @QueryParam("state") String state,
                                        @QueryParam("type") String type,
                                        @QueryParam("address") String address) throws IOException, ErrorResponseException;

    @GET
    @Path("/{coin}/wallet/{walletId}/address/{addressOrId}")
    BitGoAddressResponse getAddress(@PathParam("coin") String coin, @PathParam("walletId") String walletId, @PathParam("addressOrId") String addressOrId) throws IOException, ErrorResponseException;

    @POST
    @Path("/{coin}/wallet/{id}/address")
    @Consumes(MediaType.APPLICATION_JSON)
    BitGoAddressResponse createAddress(@PathParam("coin") String coin, @PathParam("id") String id, BitGoCreateAddressRequest request) throws IOException, ErrorResponseException;
}
