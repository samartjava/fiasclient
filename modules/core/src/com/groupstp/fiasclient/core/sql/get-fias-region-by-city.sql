--запрос получает код региона по переданному fiasId города
with region_code as (select regioncode
	from addrobj
	where aoguid = ?cityFiasId
	and aolevel in (4, 6)
	and actstatus = 1
	and enddate >= current_date
	limit 1),

--запрос получает значение fiasId и код региона по переданному значению кода региона
region AS(
	select ao.aoguid,
		ao.regioncode
	from addrobj ao
	join region_code rc on rc.regioncode = ao.regioncode
	where aolevel = 1
	limit 1)

select * from region