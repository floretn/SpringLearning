package ru.nspk.task2.service;

import ru.nspk.task2.model.Account;
import ru.nspk.task2.model.AccountId;
import ru.nspk.task2.model.EntryId;

public interface AccountService {

    EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum);

    EntryId makeReverseEntry(EntryId originalEntryId);

    Account createAccount();
}
