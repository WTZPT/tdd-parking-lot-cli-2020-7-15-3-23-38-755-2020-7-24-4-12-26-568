package com.oocl.cultivation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ParkingSmartBoyTest {

    @Test
    @DisplayName("story4-ac1 The smart parking boy will always park cars to the parking lot which contains more empty positions.")
    void should_validate_lot__when_park_give_lots() {
        //given
        List<ParkingLot> lots = new ArrayList<>();
        ParkingLot lotAHas3Car = mock(ParkingLot.class);
        when(lotAHas3Car.hasSurplus()).thenReturn(7);
        when(lotAHas3Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotBHas1Car = mock(ParkingLot.class);
        when(lotBHas1Car.hasSurplus()).thenReturn(9);
        when(lotBHas1Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotCHas2Car = mock(ParkingLot.class);
        when(lotCHas2Car.hasSurplus()).thenReturn(8);
        when(lotCHas2Car.park(isA(Car.class))).thenReturn(null);
        lots.add(lotAHas3Car);
        lots.add(lotBHas1Car);
        lots.add(lotCHas2Car);
        Car car = new Car();
        //when
        ParkingSmartBoy parkingBoy = new ParkingSmartBoy(lotAHas3Car,lotBHas1Car,lotCHas2Car);
        parkingBoy.park(car);
        //then
        verify(lotBHas1Car, times(1)).park(eq(car));
    }


}