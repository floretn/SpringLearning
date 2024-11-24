package ru.nspk.task5.service;

import ru.nspk.task5.model.AccountId;
import ru.nspk.task5.model.EntryId;

public interface AccountService {

    EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum);

    EntryId makeReverseEntry(EntryId originalEntryId);
}
