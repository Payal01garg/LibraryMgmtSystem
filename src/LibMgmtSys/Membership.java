package LibMgmtSys;

public class Membership {
        private static int nextMembershipNo = 1001;

        private int membershipNo;
        private String memberName;
        private int durationMonths;

        // Updated constructor with an additional parameter for duration
        public Membership(String memberName, int durationMonths) {
                this.membershipNo = getNextMembershipNo();
                this.memberName = memberName;
                this.durationMonths = durationMonths;
        }

        public int getMembershipNo() {
                return membershipNo;
        }

        public String getMemberName() {
                return memberName;
        }

        public int getDurationMonths() {
                return durationMonths;
        }

        // Method to update an existing membership
        public void updateMembership(int newDurationMonths) {
                // Check if the membership number is valid
                if (membershipNo <= 0) {
                        System.out.println("Invalid membership number. Update failed.");
                        return;
                }

                // Update the duration months
                durationMonths = newDurationMonths;

                System.out.println("Membership updated successfully.");
        }

        // Method to extend or cancel membership
        public void extendOrCancelMembership(boolean extendMembership) {
                // Check if the membership number is valid
                if (membershipNo <= 0) {
                        System.out.println("Invalid membership number. Operation failed.");
                        return;
                }

                if (extendMembership) {
                        // Extend membership by default 6 months
                        durationMonths += 6;
                        System.out.println("Membership extended by 6 months.");
                } else {
                        // Cancel membership
                        System.out.println("Membership canceled.");
                }
        }

        // Method to get the next unique membership number
        private static synchronized int getNextMembershipNo() {
                return nextMembershipNo++;
        }

        public static void main(String[] args) {
                // Example of adding a new membership
                Membership newMember = new Membership("John Doe", 6); // Adjust the duration as needed
                System.out.println("New Member: " + newMember.getMemberName() +
                        ", Membership No: " + newMember.getMembershipNo() +
                        ", Duration: " + newMember.getDurationMonths() + " months");

                // Example of updating and extending membership
                newMember.updateMembership(12);
                System.out.println("Updated Duration: " + newMember.getDurationMonths() + " months");

                newMember.extendOrCancelMembership(true);
                System.out.println("Extended Duration: " + newMember.getDurationMonths() + " months");
        }
}
