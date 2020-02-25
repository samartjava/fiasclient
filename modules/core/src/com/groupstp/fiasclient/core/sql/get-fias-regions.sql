select aoguid,
	formalname || ' ' || shortname as name,
	regioncode
	from addrobj
	where aolevel = 1
	and enddate >= current_date
	order by name asc