package ru.nspk.task7.service;

import ru.nspk.task7.model.AccountId;
import ru.nspk.task7.model.EntryId;

public interface AccountService {

    EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum);

    EntryId makeReverseEntry(EntryId originalEntryId);
}
