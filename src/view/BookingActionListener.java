package view;

/**
 * Callback interface so OwnerBookingCardPanel can tell its parent
 * (OwnerBookingsApproval) to refresh after Accept / Reject is clicked.
 */
public interface BookingActionListener {
    void onBookingUpdated();
}
