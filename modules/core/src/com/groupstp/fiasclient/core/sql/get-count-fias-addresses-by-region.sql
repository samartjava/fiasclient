--рекурсивный запрос строит таблицу адресообразующих эелементов
WITH RECURSIVE r AS (
    SELECT aoguid,
		parentguid,
		CASE										-- в cityFiasId пишем
			WHEN aolevel = 4 THEN aoguid			-- города
			WHEN aolevel = 6 THEN aoguid			-- деревни (посёлки, сёла и т.д.)
		END as cityFiasId,
		CASE
			WHEN (lower(shortname) LIKE '%г%'
                   OR lower(shortname) LIKE '%д%'
                   OR lower(shortname) LIKE '%с%'
                   OR lower(shortname) LIKE '%п%'
                   OR lower(shortname) LIKE '%тер%')
				THEN (shortname || ' ' || formalname)
			ELSE (formalname || ' ' || shortname)
		END AS name,
		CASE											-- в cityName пишем
			WHEN (aolevel = 4 OR aolevel = 6) 			-- города и деревни (посёлки, сёла и т.д.)
			THEN formalname
		END as cityname,
		actstatus
    FROM addrobj
	WHERE aoguid = ?parentId
    UNION
    SELECT
		addrobj.aoguid,
		addrobj.parentguid,
		CASE										-- в cityFiasId пишем
			WHEN aolevel = 4 THEN addrobj.aoguid	-- города
			WHEN aolevel = 6 THEN addrobj.aoguid	-- деревни (посёлки, сёла и т.д.)
			ELSE r.cityFiasId
		END as cityFiasId,
		CASE
			WHEN (lower(shortname) LIKE '%г%'
                 OR lower(shortname) LIKE '%д%'
                 OR lower(shortname) LIKE '%с%'
                 OR lower(shortname) LIKE '%п%'
                 OR lower(shortname) LIKE '%тер%')
				THEN (r.name || ', ' || addrobj.shortname || ' ' || addrobj.formalname)
			ELSE (r.name || ', ' || addrobj.formalname || ' ' || addrobj.shortname)
		END AS name,
		CASE											-- в cityName пишем
			WHEN (aolevel = 4 OR aolevel = 6) 			-- города и деревни (посёлки, сёла и т.д.)
			THEN addrobj.formalname
			ELSE r.cityname
		END as cityname,
		addrobj.actstatus
    FROM addrobj
        JOIN r ON addrobj.parentguid = r.aoguid
	WHERE addrobj.enddate >= current_date),

--запрос объединяет таблицу адресообразующих элементов и таблицу домов, формируя полные адреса
house as(SELECT r.cityFiasId AS cityFiasId,
	r.cityname,
	CASE
		WHEN h.housenum <> '' THEN r.name || ', ' || h.housenum
		WHEN h.buildnum <> '' THEN (r.name || ', ' || h.buildnum || ' ' || h.strucnum)
		ELSE r.name || ', ' || h.strucnum
	END AS house
	   FROM ***house*** AS h
JOIN r ON h.aoguid = r.aoguid
WHERE r.actstatus = 1
	and h.enddate >= current_date
	and h.eststatus in (0, 2, 3))

select cityfiasid,
	cityname,
	count(*) count_addresses
from house
group by cityname, cityfiasid
order by cityname asc
