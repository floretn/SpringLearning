package ru.nspk.task6.service;

import ru.nspk.task6.model.AccountId;
import ru.nspk.task6.model.EntryId;

public interface AccountService {

    EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum);

    EntryId makeReverseEntry(EntryId originalEntryId);
}
