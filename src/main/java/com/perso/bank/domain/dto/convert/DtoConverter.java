package com.perso.bank.domain.dto.convert;

import java.util.List;

public interface DtoConverter<X, Y> {

	X createFromDto(Y dto);
	Y createDto( X source );
	List<X> createFromDto(List<Y> dto);
	List<Y> createDto( List<X> source );
}
