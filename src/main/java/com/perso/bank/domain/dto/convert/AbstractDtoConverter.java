package com.perso.bank.domain.dto.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractDtoConverter<X,Y> implements DtoConverter<X, Y>{

	@Override
	public List<X> createFromDto(List<Y> dto) {
		return Optional.ofNullable( dto )
			.orElse(new ArrayList<>())
			.stream()
			.map( e -> createFromDto( e ) )
			.collect( Collectors.toList() );
	}

	@Override
	public List<Y> createDto(List<X> source) {
		return Optional.ofNullable( source )
				.orElse(new ArrayList<>())
				.stream()
				.map( e -> createDto( e ) )
				.collect( Collectors.toList() );
	}
	
}
