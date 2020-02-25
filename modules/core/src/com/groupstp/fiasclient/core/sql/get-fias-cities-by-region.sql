select aoguid,
	CASE
		WHEN (lower(shortname) LIKE '%г%'
			 OR lower(shortname) LIKE '%д%'
			 OR lower(shortname) LIKE '%с%'
			 OR lower(shortname) LIKE '%п%'
			 OR lower(shortname) LIKE '%тер%')
		THEN (shortname || ' ' || formalname)
		ELSE (formalname || ' ' || shortname)
	END AS name
	from addrobj
	where 
	regioncode = ?regionCode
	and aolevel in (4, 6)
	and actstatus = 1
	and enddate >= current_date
	order by name asc