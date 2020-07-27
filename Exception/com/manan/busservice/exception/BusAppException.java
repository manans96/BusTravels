/**
 * 
 */
package com.manan.busservice.exception;

import com.manan.busservice.response.EntityResponse;

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

		private EntityResponse entity;
		
		public EntityNotFoundException(EntityResponse entity) {
			super();
			this.entity = entity;
		}

		public EntityNotFoundException(EntityResponse entity, String message) {
			super(message);
			this.entity = entity;
		}
		
		public EntityResponse getEntity() {
			return this.entity;
		}
	}
	
	public static class DuplicateEntityException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -824903261791335262L;

		private EntityResponse entity;

		public DuplicateEntityException(EntityResponse entity) {
			super();
			this.entity = entity;
		}

		public DuplicateEntityException(EntityResponse entity, String message) {
			super(message);
			this.entity = entity;
		}
		
		public EntityResponse getEntity() {
			return this.entity;
		}
	}

	public static class BadRequestException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2064428674233075906L;
		
		private EntityResponse entity;

		public BadRequestException(EntityResponse entity) {
			super();
			this.entity = entity;
		}

		public BadRequestException(EntityResponse entity, String message) {
			super(message);
			this.entity = entity;
		}

		public EntityResponse getEntity() {
			return entity;
		}
	}

	public static class WrongCredentialsException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1651100744220140201L;

		private EntityResponse entity;

		public WrongCredentialsException(EntityResponse entity) {
			super();
			this.entity = entity;
		}

		public WrongCredentialsException(EntityResponse entity, String message) {
			super(message);
			this.entity = entity;
		}

		public EntityResponse getEntity() {
			return entity;
		}
		
	}

	public static class InternalServerException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1578854205725465642L;

		private EntityResponse entity;

		public InternalServerException(EntityResponse entity) {
			super();
			this.entity = entity;
		}

		public InternalServerException(EntityResponse entity, String message) {
			super(message);
			this.entity = entity;
		}

		public EntityResponse getEntity() {
			return entity;
		}
	}

	public static class ForbiddenException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4227305455472382045L;
		
		private EntityResponse entity;

		public ForbiddenException(EntityResponse entity) {
			super();
			this.entity = entity;
		}

		public ForbiddenException(EntityResponse entity, String message) {
			super(message);
			this.entity = entity;
		}

		public EntityResponse getEntity() {
			return entity;
		}
	}

}
