/*
    This file is part of Peers.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Copyright 2007, 2008 Yohann Martineau 
*/

package net.sourceforge.peers.sip.transaction;

public class InviteClientTransactionStateProceeding extends
        InviteClientTransactionState {

    public InviteClientTransactionStateProceeding(String id,
            InviteClientTransaction inviteClientTransaction) {
        super(id, inviteClientTransaction);
    }

    @Override
    public void received1xx() {
        InviteClientTransactionState nextState = inviteClientTransaction.PROCEEDING;
        inviteClientTransaction.setState(nextState);
        log(nextState);
        inviteClientTransaction.transactionUser.provResponseReceived(
                inviteClientTransaction.getLastResponse(), inviteClientTransaction);
    }
    
    @Override
    public void received2xx() {
        InviteClientTransactionState nextState = inviteClientTransaction.TERMINATED;
        inviteClientTransaction.setState(nextState);
        log(nextState);
        inviteClientTransaction.transactionUser.successResponseReceived(
                inviteClientTransaction.getLastResponse(), inviteClientTransaction);
    }
    
    @Override
    public void received300To699() {
        InviteClientTransactionState nextState = inviteClientTransaction.COMPLETED;
        inviteClientTransaction.setState(nextState);
        log(nextState);
        inviteClientTransaction.transactionUser.errResponseReceived(
                inviteClientTransaction.getLastResponse());
        inviteClientTransaction.createAndSendAck();
    }
    
    
}
