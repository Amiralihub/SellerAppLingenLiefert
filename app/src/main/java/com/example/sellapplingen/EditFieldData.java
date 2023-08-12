package com.example.sellapplingen;

    public class EditFieldData {
        private String fieldName;
        private String oldValue;
        private String newValue;

        public EditFieldData(String fieldName, String oldValue) {
            this.fieldName = fieldName;
            this.oldValue = oldValue;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getOldValue() {
            return oldValue;
        }

        public String getNewValue() {
            return newValue;
        }

        public void setNewValue(String newValue) {
            this.newValue = newValue;
        }
    }


