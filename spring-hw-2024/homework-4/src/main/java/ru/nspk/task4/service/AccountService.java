package ru.nspk.task4.service;

import ru.nspk.task4.model.Account;
import ru.nspk.task4.model.AccountId;
import ru.nspk.task4.model.EntryId;

public interface AccountService {

    EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum);

    EntryId makeReverseEntry(EntryId originalEntryId);

    Account createAccount();
}
