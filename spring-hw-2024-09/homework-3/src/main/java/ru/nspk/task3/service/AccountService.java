package ru.nspk.task3.service;

import ru.nspk.task3.model.Account;
import ru.nspk.task3.model.AccountId;
import ru.nspk.task3.model.EntryId;

public interface AccountService {

    EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum);

    EntryId makeReverseEntry(EntryId originalEntryId);

    Account createAccount();
}
