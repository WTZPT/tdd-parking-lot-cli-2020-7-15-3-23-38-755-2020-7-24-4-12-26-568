package com.oocl.cultivation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class ParkingSuperSmartBoyTest {
    @Test
    @DisplayName("story5-AC1 The super smart parking boy will always park cars to the parking lot which has a larger available position rate (positions available / total capacity).")
    void should_validate_largest_position_rate_lot_when_park_give_lots() {
        //given
        ParkingLot lotAHas3Car = mock(ParkingLot.class);
        when(lotAHas3Car.getPositionRate()).thenReturn(0.7);
        when(lotAHas3Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotBHas1Car = mock(ParkingLot.class);
        when(lotBHas1Car.getPositionRate()).thenReturn(0.9);
        when(lotBHas1Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotCHas2Car = mock(ParkingLot.class);
        when(lotCHas2Car.getPositionRate()).thenReturn(0.8);
        when(lotCHas2Car.park(isA(Car.class))).thenReturn(null);
        Car car = new Car();
        //when
        ParkingSuperSmartBoy parkingBoy = new ParkingSuperSmartBoy(lotAHas3Car,lotBHas1Car,lotCHas2Car);
        parkingBoy.park(car);
        //then
        verify(lotBHas1Car, times(1)).park(eq(car));
    }

}