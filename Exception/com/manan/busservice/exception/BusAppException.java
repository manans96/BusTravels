/**
 * 
 */
package com.manan.busservice.exception;

import com.manan.busservice.response.ResponseEntity;

/**
 * @author Manan Sanghvi
 *
 */
public class BusAppException {

	public static class EntityNotFoundException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8717991220116242274L;

		private ResponseEntity entity;
		
		public EntityNotFoundException(ResponseEntity entity) {
			super();
			this.entity = entity;
		}

		public EntityNotFoundException(ResponseEntity entity, String message) {
			super(message);
			this.entity = entity;
		}
		
		public ResponseEntity getEntity() {
			return this.entity;
		}
	}
	
	public static class DuplicateEntityException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -824903261791335262L;

		private ResponseEntity entity;

		public DuplicateEntityException(ResponseEntity entity) {
			super();
			this.entity = entity;
		}

		public DuplicateEntityException(ResponseEntity entity, String message) {
			super(message);
			this.entity = entity;
		}
		
		public ResponseEntity getEntity() {
			return this.entity;
		}
	}

	public static class ValidationException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2064428674233075906L;
		
		private ResponseEntity entity;

		public ValidationException(ResponseEntity entity) {
			super();
			this.entity = entity;
		}

		public ValidationException(ResponseEntity entity, String message) {
			super(message);
			this.entity = entity;
		}

		public ResponseEntity getEntity() {
			return entity;
		}
	}

	public static class WrongCredentialsException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1651100744220140201L;

		private ResponseEntity entity;

		public WrongCredentialsException(ResponseEntity entity) {
			super();
			this.entity = entity;
		}

		public WrongCredentialsException(ResponseEntity entity, String message) {
			super(message);
			this.entity = entity;
		}

		public ResponseEntity getEntity() {
			return entity;
		}
		
	}
}
