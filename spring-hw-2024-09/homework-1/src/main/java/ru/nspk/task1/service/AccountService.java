package ru.nspk.task1.service;

import ru.nspk.task1.model.AccountId;
import ru.nspk.task1.model.EntryId;

public interface AccountService {

    EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum);

    EntryId makeReverseEntry(EntryId originalEntryId);
}
